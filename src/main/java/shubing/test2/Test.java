package shubing.test2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jiashubing
 * @since 2018/3/22
 */
public class Test {
    public static void main(String[] args) {

//        String s="<p><span style=>各学院：</span></p><p><span style=>各学院1：</span></p>";
//        String regex=">(([^\\x00-\\xff]|[0-9])+?)</span>";
        String s = "search-tab-content-item flex\">\n" +
                "\t                        <a href=\"/event/9432964395012?utm_source=%e5%8f%91%e7%8e%b0%e6%b4%bb%e5%8a%a8%e9%a1%b5&amp;utm_medium=&amp;utm_campaign=eventspage\" target=\"_blank\">\n" +
                "\t\t                        <img class=\"item-logo\" src=\"http://cdn.huodongxing.com/logo/201803/9432964395012/333006745234371_v2.jpg\" alt=\"【野蘑菇】【京北大峡谷】“香屯”看最美古长城 感受山谷间纯净的美！\" />\n" +
                "\t                        </a>\n" +
                "\t                        <div class=\"search-tab-content-item-right\">\n" +
                "\t\t                        <div class=\"flex item-title-wrap\">\n" +
                "\t\t\t                        <a class=\"item-title\" href=\"/event/9432964395012?utm_source=%e5%8f%91%e7%8e%b0%e6%b4%bb%e5%8a%a8%e9%a1%b5&amp;utm_medium=&amp;utm_campaign=eventspage\" target=\"_blank\">【野蘑菇】【京北大峡谷】“香屯”看最美古长城 感受山谷间纯净的美！</a>\n" +
                "\t\t                        </div>\n" +
                "\t\t                        <div class=\"item-div-wrap flex\">\n" +
                "\t\t\t                       <div class=\"browse-div flex\"><span class=\"icon browse-icon\"></span>45</div>\n" +
                "\t\t\t                        <div class=\"like-div flex\"><span class=\"icon like-icon\"></span>23</div>\n" +
                "\t\t                        </div>\n" +
                "\t\t                        <p class=\"item-data flex\"><span class=\"item-data-icon icon\"></span>2018.04.01-2018.04.01</p>\n" +
                "\t\t                        <p class=\"item-dress flex\"><span class=\"item-dress-icon icon\"></span>北京朝阳惠新西街南口</p>\n" +
                "\t\t                        <div class=\"item-bottom flex\">\n" +
                "\t\t\t                        <a href=\"/event/9432964395012?utm_source=%e5%8f%91%e7%8e%b0%e6%b4%bb%e5%8a%a8%e9%a1%b5&amp;utm_medium=&amp;utm_campaign=eventspage\" target=\"_blank\">\n" +
                "\t\t\t\t                        <div class=\"item-bottom-left flex\">\n" +
                "\t\t\t\t\t                        <img class=\"user-logo\" src=\"http://qzapp.qlogo.cn/qzapp/100306609/B12374BCABC64AEC3568F034377E5DCC/30\" alt=\"猎人\" title=\"猎人\" />\n" +
                "\t\t\t\t\t                        <p class=\"user-name\">猎人</p>\n" +
                "\t\t\t\t                        </div>\n" +
                "\t\t\t                        </a>\n" +
                "\t\t\t                        <div class=\"item-bottom-right\"><a href=\"/event/9432964395012?utm_source=%e5%8f%91%e7%8e%b0%e6%b4%bb%e5%8a%a8%e9%a1%b5&amp;utm_medium=&amp;utm_campaign=eventspage\" target=\"_blank\" class=\"button-common button-apply\">立即报名";
//        String regex="(<tr height=\"30\")([\\s\\S]*?)(</tr>)";
//        Pattern p=Pattern.compile(regex);
//        Matcher m=p.matcher(s);
//        while(m.find()){
//            System.out.print(m.group());
//            System.out.println("************");
//        }
        specialSplit(s);

    }

    private static void specialSplit(String s) {
        String regex = "(search-tab-content-item flex)([\\s\\S]*?)(立即报名)";
        String regex1 = "(item-logo)([\\s\\S]*?)(</a>)";
        String regex2 = "(item-data-icon)(.*?)(</p>)";
        String regex3 = "(item-dress-icon)(.*?)(</p>)";
        String regex4 = "(item-bottom-right)(.*?)(立即报名)";
//        String regex4 = "(timestyle55496\" >)(.*?)(&nbsp;)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        while (m.find()) {
            String tmp = m.group();
//            System.out.print(m.group());
//            System.out.println("************");
            Pattern p1 = Pattern.compile(regex1);
            Matcher m1 = p1.matcher(tmp);
            if (m1.find()) {
                String str = m1.group();
                System.out.println(str);
                int t1 = str.indexOf('\"');
                int t2 = str.indexOf('\"', t1 + 1);
                int t3 = str.indexOf('\"', t2 + 1);
                int t4 = str.indexOf('\"', t3 + 1);
                int t5 = str.indexOf('\"', t4 + 1);
//                System.out.println(str.indexOf('\"',3));
                System.out.println(str.substring(t2 + 1, t3));
                System.out.println(str.substring(t4 + 1, t5));
            }
            Pattern p2 = Pattern.compile(regex2);
            Matcher m2 = p2.matcher(tmp);
            if (m2.find()) {
                String str = m2.group();
                System.out.println(str.substring(str.indexOf("</span>") + 7, str.lastIndexOf('<')));
            }
            Pattern p3 = Pattern.compile(regex3);
            Matcher m3 = p3.matcher(tmp);
            if (m3.find()) {
                String str = m3.group();
                System.out.println(str.substring(str.indexOf("</span>") + 7, str.lastIndexOf('<')));
            }
            Pattern p4 = Pattern.compile(regex4);
            Matcher m4 = p4.matcher(tmp);
            if (m4.find()) {
                String str = m4.group();
                int t1 = str.indexOf("href") + 6;
                int t2 = str.indexOf('\"', t1 + 10);
                System.out.println("http://www.huodongxing.com/" + str.substring(t1, t2));
            }
        }
    }

}
