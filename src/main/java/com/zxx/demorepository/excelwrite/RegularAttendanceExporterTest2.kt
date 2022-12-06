package com.h4kit.xjwj.attendance.excel.export.handle

import org.junit.*
import org.springframework.boot.test.context.*

import com.alibaba.excel.EasyExcel

import java.time.*
import com.alibaba.excel.annotation.ExcelProperty
import com.zxx.demorepository.excelwrite.*
import org.apache.poi.ss.usermodel.*
import java.time.format.*


/**
 *
 *
 *
 *
 *
 * @author kam
 * @since 2022/12/01
 */
@SpringBootTest
internal class RegularAttendanceExporterTest2 {

    val path = "D:\\NewWorkSpace\\DemoRepository\\src\\main\\java\\com\\zxx\\demorepository\\excelwrite\\"

    class PersonData {
        @ExcelProperty("序号")
        var no: String? = null

        @ExcelProperty("科室")
        var name: String? = null

        @ExcelProperty("姓名")
        var dept: String? = null
    }

    fun header(beginDate: String, endDate: String): MutableList<MutableList<String>> {
        val header = mutableListOf<MutableList<String>>()
        val mainTitle = "考勤记录表"
        val subTitle1 = "统计日期：$beginDate 至 $endDate"
        val subTitle2 = "报表生成时间：${LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))}"
        header.add(mutableListOf(mainTitle, subTitle1, subTitle2, "序号"))
        header.add(mutableListOf(mainTitle, subTitle1, subTitle2, "姓名"))
        header.add(mutableListOf(mainTitle, subTitle1, subTitle2, "科室"))
        data2().forEach {
            header.add(mutableListOf(it.dayIndex!!, it.week!!, it.recordTitle1!!))
            header.add(mutableListOf(it.dayIndex!!, it.week!!, it.recordTitle2!!))
        }
        return header
    }

    @Test
    fun export() {
        val fileName: String = path + "compositeFill11111.xlsx"
//        val headerStyle = WriteCellStyle()
//        headerStyle.fillForegroundColor = IndexedColors.LIGHT_TURQUOISE.index;
//
//        val contentStyle = WriteCellStyle()
//        val horizontalCellStyleStrategy = HorizontalCellStyleStrategy(headerStyle, contentStyle)

        val nowDate = LocalDate.now()
        val nowDateStr = nowDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val header = header(nowDateStr, nowDate.plusDays(2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))

        val headerStyles = mutableListOf<VerticalHeadColorStyle>()
        headerStyles.add(
            VerticalHeadColorStyle(0..2, IndexedColors.LIGHT_TURQUOISE.index)
        )
        headerStyles.add(
            VerticalHeadColorStyle(3..header.size, IndexedColors.LEMON_CHIFFON.index)
        )

        val data = data1()

        EasyExcel.write(fileName)
            .head(header)
            .registerWriteHandler(AttendanceExcelHeaderStyleStrategy(headerStyles))
            .registerWriteHandler(
                AttendanceExcelCellWithMergeStrategy(
                    data.size,
                    headLength = header.size,
                    2,
                    notMergeRows = intArrayOf(2)
                )
            )
            .registerWriteHandler(AttendanceExcelColumnWidthStyleStrategy())
            .registerWriteHandler(ExportDetailWithStatisticsFreezeHandler)
//            .registerWriteHandler(LongestMatchColumnWidthStyleStrategy())
            .sheet("default")
            .doWrite(data)
    }

    fun data2(): List<TestData2> {
        var weekIndex = 1
        return (1..10).map {
            TestData2().apply {
                this.dayIndex = (it + 10).toString()
                this.recordTitle1 = " 首 次    "
                this.recordTitle2 = "最后一次   "
                this.week = kotlin.run {
                    if (weekIndex == 7) {
                        weekIndex = 1
                    }
                    weekIndex++
                    weekChineseMapping[DayOfWeek.of(weekIndex)].toString()
                }
            }
        }
    }

    fun data1(): MutableList<MutableList<String>> {
        val name = listOf("a", "a", "a", "a", "b", "b", "b", "c", "c")
        val iterator = name.iterator()
        return (1..10).map {
            //每个人信息
            val contentLine = mutableListOf<String>(
                it.toString(),
                if (iterator.hasNext()) iterator.next() else "",
                TradeNoUtil.tradeNo(),
            )

            val now = LocalDateTime.now()

            //时间点
            (1..10).forEach {
                contentLine.add(now.format(DateTimeFormatter.ofPattern("HH:mm")))
                contentLine.add(now.plusMinutes(20).format(DateTimeFormatter.ofPattern("HH:mm")))
            }
            contentLine
        }.toMutableList()
    }

    class TestData2 {
        var dayIndex: String? = null
        var recordTitle1: String? = null
        var recordTitle2: String? = null
        var week: String? = null
    }
}

val weekChineseMapping = mutableMapOf(
    DayOfWeek.MONDAY to "周一",
    DayOfWeek.TUESDAY to "周二",
    DayOfWeek.WEDNESDAY to "周三",
    DayOfWeek.THURSDAY to "周四",
    DayOfWeek.FRIDAY to "周五",
    DayOfWeek.SATURDAY to "周六",
    DayOfWeek.SUNDAY to "周日",
)

fun DayOfWeek.toChinese(): String {
    return weekChineseMapping[this]!!
}

