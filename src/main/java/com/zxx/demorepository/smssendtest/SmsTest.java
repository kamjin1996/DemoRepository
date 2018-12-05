package com.zxx.demorepository.smssendtest;

import com.dahantc.api.sms.json.JSONHttpClient;
import org.apache.log4j.Logger;

import java.util.UUID;


/**
 * @auther: kam
 * @date: 20:00 2018-12-04
 * @description:
 */
public class SmsTest {
    private static final Logger LOG = Logger.getLogger(SmsTest.class);
    private static String account = "dh58741";// 用户名（必填）
    private static String password = "oSJV0EkP";// 密码（必填,明文）
    private static String phone = "13069307821"; // 手机号码（必填,多条以英文逗号隔开）
    public static String sign = "【测试签名1】"; // 短信签名（必填）
    public static String subcode = ""; // 子号码（可选）
    public static String msgid = UUID.randomUUID().toString().replace("-", ""); // 短信id，查询短信状态报告时需要，（可选）
    public static String sendtime = ""; // 定时发送时间（可选）

    public static void main(String[] args) {
        try {
            String content = "您的验证码是:123456";// 短信内容（必填）

            JSONHttpClient jsonHttpClient = new JSONHttpClient("http://www.dh3t.com");
            jsonHttpClient.setRetryCount(1);
            String sendhRes = jsonHttpClient.sendSms(account, password, phone, content, sign, subcode);
            LOG.info("提交单条普通短信响应：" + sendhRes);

            //
            // List<SmsData> list = new ArrayList<SmsData>();
            // list.add(new
            // SmsData("11111111,15711666133,1738786465,44554545",
            // content, msgid, sign, subcode, sendtime));
            // list.add(new SmsData("15711777134", content, msgid, sign,
            // subcode, sendtime));
            // String sendBatchRes = jsonHttpClient.sendBatchSms(account,
            // password, list);
            // LOG.info("提交批量短信响应：" + sendBatchRes);
            //
            // String reportRes = jsonHttpClient.getReport(account, password);
            // LOG.info("获取状态报告响应：" + reportRes);
            //
            // String smsRes = jsonHttpClient.getSms(account, password);
            // LOG.info("获取上行短信响应：" + smsRes);
            //
            // String balanceRes = jsonHttpClient.getBalance(account, password);
            // LOG.info("获取余额短信响应：" + balanceRes);

        } catch (Exception e) {

            LOG.error("应用异常", e);
        }


    }

}
