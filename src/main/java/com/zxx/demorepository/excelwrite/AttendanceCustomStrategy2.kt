package com.zxx.demorepository.excelwrite

import com.alibaba.excel.enums.*
import com.alibaba.excel.metadata.*
import com.alibaba.excel.metadata.data.*
import com.alibaba.excel.util.*
import com.alibaba.excel.util.MapUtils
import com.alibaba.excel.write.handler.*
import com.alibaba.excel.write.handler.context.*
import com.alibaba.excel.write.metadata.holder.*
import com.alibaba.excel.write.metadata.style.*
import com.alibaba.excel.write.style.*
import com.alibaba.excel.write.style.column.*
import org.apache.commons.collections4.*
import org.apache.poi.hssf.record.cf.BorderFormatting.*
import org.apache.poi.hssf.usermodel.*
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.util.*
import sun.plugin.dom.css.*

/**
 * <p>
 *
 * </p>
 *
 * @author kam
 * @since 2022/12/05
 */
data class CellRange(val rowIndexRange: IntRange, val columnIndexRange: IntRange)

class AttendanceExcelCellWithMergeStrategy(
    private val dataSize: Int,
    private val headLength: Int,
    private val notMergeBeginColIndex: Int,
    private val notMergeRows: IntArray,
    private val contentCellNotMergeRanges: List<CellRange> = mutableListOf(
        CellRange(
            rowIndexRange = 3..dataSize,
            columnIndexRange = 3..headLength,
        )
    ),
) :
    CellWriteHandler {

    override fun beforeCellCreate(
        writeSheetHolder: WriteSheetHolder?,
        writeTableHolder: WriteTableHolder?,
        row: Row?,
        head: Head?,
        columnIndex: Int?,
        relativeRowIndex: Int?,
        isHead: Boolean?
    ) {
        super.beforeCellCreate(writeSheetHolder, writeTableHolder, row, head, columnIndex, relativeRowIndex, isHead)
    }

    override fun afterCellCreate(
        writeSheetHolder: WriteSheetHolder?,
        writeTableHolder: WriteTableHolder?,
        cell: Cell?,
        head: Head?,
        relativeRowIndex: Int?,
        isHead: Boolean?
    ) {
        super.afterCellCreate(writeSheetHolder, writeTableHolder, cell, head, relativeRowIndex, isHead)
    }

    override fun afterCellDataConverted(
        writeSheetHolder: WriteSheetHolder?,
        writeTableHolder: WriteTableHolder?,
        cellData: WriteCellData<*>?,
        cell: Cell?,
        head: Head?,
        relativeRowIndex: Int?,
        isHead: Boolean?
    ) {
        super.afterCellDataConverted(writeSheetHolder, writeTableHolder, cellData, cell, head, relativeRowIndex, isHead)
    }

    override fun afterCellDispose(
        context: CellWriteHandlerContext
    ) {
        val cell = context.cell
        val writeSheetHolder = context.writeSheetHolder

        if (cell != null && writeSheetHolder != null) {
            // 当前行
            val curRowIndex = cell.rowIndex
            // 当前列
            val curColIndex = cell.columnIndex
            //遍历数据行数据，获取sheet的合并单元格，根据条件移除合并区域
            if (notMergeRows.isNotEmpty()) {
                notMergeRows.forEach {
                    if (curRowIndex == it && curColIndex > notMergeBeginColIndex /*第几列开始*/ && curColIndex < headLength) {
                        removeChildTitleMerge(writeSheetHolder, curRowIndex, curColIndex)
                    }
                }
            }

            //指定区域移除合并的cell
            if (contentCellNotMergeRanges.isNotEmpty()) {
                contentCellNotMergeRanges.forEach {
                    removeContentRegionMerge(
                        writeSheetHolder,
                        it.rowIndexRange,
                        it.columnIndexRange,
                    )
                }
            }

            //自定义样式
            customStyle(context, 4..5, 0..2) {
                it.fillForegroundColor = IndexedColors.RED.index
                it.fillPatternType = FillPatternType.SOLID_FOREGROUND
            }

            customStyle(context, 4, 3) {
                it.fillForegroundColor = IndexedColors.YELLOW.index
                it.fillPatternType = FillPatternType.SOLID_FOREGROUND
            }
        }
    }

    private fun customStyle(
        context: CellWriteHandlerContext,
        rowIndexRange: IntRange,
        columnIndexRange: IntRange,
        styleCustom: (style: WriteCellStyle) -> Unit
    ) {
        val cell = context.cell
        if (cell.rowIndex in rowIndexRange && cell.columnIndex in columnIndexRange) {
            val cellData = context.firstCellData
            styleCustom.invoke(cellData.orCreateStyle)
        }
    }

    private fun customStyle(
        context: CellWriteHandlerContext,
        rowIndex: Int,
        columnIndex: Int,
        styleCustom: (style: WriteCellStyle) -> Unit
    ) {
        customStyle(
            context = context,
            rowIndexRange = rowIndex..rowIndex,
            columnIndexRange = columnIndex..columnIndex,
            styleCustom = styleCustom
        )
    }

    /**
     * 取消多个合并单元格
     *
     * @param sheet
     * @param startRow    开始行号
     * @param endRow      结束行号
     * @param startColumn 开始列号
     * @param endColumn   结束列号
     */
    private fun removeContentRegionMerge(
        writeSheetHolder: WriteSheetHolder,
        rowIndexRange: IntRange?,
        columnIndexRange: IntRange,
    ) {
        val sheet = writeSheetHolder.sheet
        val startRowIndex1 = rowIndexRange?.first ?: sheet.firstRowNum
        val endRowIndex1 = rowIndexRange?.last ?: sheet.lastRowNum;

        //获取所有的单元格
        val mergeRegions = sheet.mergedRegions;
        //用于保存要移除的那个合并单元格序号
        val indexList = mutableListOf<Int>();
        for (i in mergeRegions.indices) {
            //获取第i个单元格
            val ca = mergeRegions[i]
            val firstColumn = ca.firstColumn;
            val lastColumn = ca.lastColumn;
            val firstRow = ca.firstRow;
            val lastRow = ca.lastRow;
            if (startRowIndex1 <= firstRow && endRowIndex1 >= lastRow && columnIndexRange.first <= firstColumn && columnIndexRange.last >= lastColumn) {
                indexList.add(i);
            }
        }
        sheet.removeMergedRegions(indexList);
    }

    private fun removeChildTitleMerge(writeSheetHolder: WriteSheetHolder, curRowIndex: Int, curColIndex: Int) {
        //获取sheet页
        val sheet = writeSheetHolder.sheet
        val mergeRegions = sheet.mergedRegions
        //遍历合并单元格，判断合并单元格是否包含子标题的位置，包含则移除
        for (i in mergeRegions.indices) {
            val cellRangeAddr = mergeRegions[i]
            // 若单元格已经被合并，则移除合并的单元格
            if (cellRangeAddr.isInRange(curRowIndex, curColIndex) && cellRangeAddr.isInRange(
                    curRowIndex,
                    curColIndex + 1
                )
            ) {
                sheet.removeMergedRegion(i)
                break
            }
        }
    }
}

interface HeadStyle
data class VerticalHeadColorStyle(
    val columnIndexRange: IntRange,
    val indexColor: Short?,
) : HeadStyle

class AttendanceExcelHeaderStyleStrategy(
    private var headStyles: MutableList<VerticalHeadColorStyle>
) : AbstractVerticalCellStyleStrategy() {

    override fun headCellStyle(head: Head?): WriteCellStyle {
        if (head == null) {
            return WriteCellStyle()
        }
        // 获取样式实例
        val headWriteCellStyle = WriteCellStyle();
        // 获取字体实例
        val headWriteFont = WriteFont();
//        // 设置字体样式
//        headWriteFont.setFontName("宋体");
        // 设置字体大小
//        headWriteFont.setFontHeightInPoints((short)14);
        // 边框
        headWriteFont.setBold(true);
        headWriteCellStyle.writeFont = headWriteFont;
        //根据条件设置表头
        headStyles.forEach {
            if (head.columnIndex in it.columnIndexRange) {
                headWriteCellStyle.fillForegroundColor = it.indexColor;
            }
        }
        return headWriteCellStyle;

    }
}

class AttendanceExcelColumnWidthStyleStrategy : AbstractColumnWidthStyleStrategy() {

    private val cache: MutableMap<Int, MutableMap<Int, Int>> = MapUtils.newHashMapWithExpectedSize(8)

    override fun setColumnWidth(
        writeSheetHolder: WriteSheetHolder, cellDataList: List<WriteCellData<*>?>, cell: Cell,
        head: Head,
        relativeRowIndex: Int, isHead: Boolean
    ) {
        val needSetWidth = isHead || !CollectionUtils.isEmpty(cellDataList)
        if (!needSetWidth) {
            return
        }
        val maxColumnWidthMap = cache.computeIfAbsent(
            writeSheetHolder.sheetNo
        ) { key: Int? ->
            HashMap(
                16
            )
        }
        var columnWidth = dataLength(cellDataList, cell, isHead)
        if (columnWidth < 0) {
            return
        }
        if (columnWidth > MAX_COLUMN_WIDTH) {
            columnWidth = MAX_COLUMN_WIDTH
        }
        val maxColumnWidth = maxColumnWidthMap[cell.columnIndex]
        if (maxColumnWidth == null || columnWidth > maxColumnWidth) {
            maxColumnWidthMap[cell.columnIndex] = columnWidth

            //                writeSheetHolder.sheet.setColumnWidth(cell.columnIndex, columnWidth * 256)
            writeSheetHolder.sheet.setColumnWidth(cell.columnIndex, columnWidth * 200)
        }
    }

    private fun dataLength(cellDataList: List<WriteCellData<*>?>, cell: Cell, isHead: Boolean): Int {
        if (isHead) {
            return cell.stringCellValue.toByteArray().size
        }
        val cellData = cellDataList[0]
        val type = cellData!!.type ?: return -1
        return when (type) {
            CellDataTypeEnum.STRING -> cellData.stringValue.toByteArray().size
            CellDataTypeEnum.BOOLEAN -> cellData.booleanValue.toString().toByteArray().size
            CellDataTypeEnum.NUMBER -> cellData.numberValue.toString().toByteArray().size
            else -> -1
        }
    }

    companion object {
        private const val MAX_COLUMN_WIDTH = 255
    }
}

/**
 * 导出详情和汇总的冻结handler对象
 */
object ExportDetailWithStatisticsFreezeHandler : SheetWriteHandler {
    override fun beforeSheetCreate(writeWorkbookHolder: WriteWorkbookHolder, writeSheetHolder: WriteSheetHolder) {}
    override fun afterSheetCreate(writeWorkbookHolder: WriteWorkbookHolder, writeSheetHolder: WriteSheetHolder) {
        val sheet: Sheet = writeSheetHolder.sheet
        sheet.createFreezePane(3, 0, 2, 0)
        sheet.setAutoFilter(CellRangeAddress.valueOf("1:1"))
    }
}