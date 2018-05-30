package shubing.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jiashubing
 * @since 2018/3/22
 */
public class Test {
    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        List<String> list2 = list.subList(0,2);
        for (String tmp : list2){
            System.out.println(tmp);
        }

        String s = "<tr height=\"30\">  \n" +
                "        <td style=\"font-size:9pt\">\n" +
                "         <span  class=\"leaderfont55496\" >·</span>\n" +
                "          \n" +
                "            \n" +
                "          <a class=\"c55496\" href=\"xx_nry.jsp?urltype=news.NewsContentUrl&wbtreeid=1074&wbnewsid=7470&archive=0\" target=\"_blank\"   title=\"信息管理系开展信息管理与信息系统“十三五”省特色专业分年度建设计划及对策研讨会\">  \n" +
                "                        信息管理系开展信息管理与信息系统“十三五”省特色专业分年度建设计划及对策研讨会\n" +
                "            </a>\n" +
                "        \n" +
                "        </span>\n" +
                "        </td>  \n" +
                "        \n" +
                "        <td width=1% nowrap valign=\"middle\" align=right  class=\"timestyle55496\" >2017/10/16&nbsp;</td>     \n" +
                "        \n" +
                "      </tr>\n" +
                "\n" +
                "      \n" +
                "      <tr height=\"1\">\n" +
                "      <td colspan=\"3\" align=\"center\"><hr style=\"border-style:dashed;border-color:#999999;width:100%;height:1px;border-width:1px 0px 0px 0px;visibility:inherit\"/></td>\n" +
                "      </tr>\n" +
                "      \n" +
                "     \n" +
                "      <tr height=\"30\">  \n" +
                "        <td style=\"font-size:9pt\">\n" +
                "         <span  class=\"leaderfont55496\" >·</span>\n" +
                "          \n" +
                "            \n" +
                "          <a class=\"c55496\" href=\"xx_nry.jsp?urltype=news.NewsContentUrl&wbtreeid=1074&wbnewsid=7467&archive=0\" target=\"_blank\"   title=\"领略信息科技力量——信工学子参加2017云栖大会\">  \n" +
                "                        领略信息科技力量——信工学子参加2017云栖大会\n" +
                "            </a>\n" +
                "        \n" +
                "        </span>\n" +
                "        </td>  \n" +
                "        \n" +
                "        <td width=1% nowrap valign=\"middle\" align=right  class=\"timestyle55496\" >2017/10/16&nbsp;</td>     \n" +
                "        \n" +
                "      </tr>\n" +
                "\n" +
                "      \n" +
                "     \n" +
                "      <tr height=\"30\">  \n" +
                "        <td style=\"font-size:9pt\">\n" +
                "         <span  class=\"leaderfont55496\" >·</span>\n" +
                "          \n" +
                "            \n" +
                "          <a class=\"c55496\" href=\"xx_nry.jsp?urltype=news.NewsContentUrl&wbtreeid=1074&wbnewsid=7463&archive=0\" target=\"_blank\"   title=\"信工学院举行国家级课题申报经验交流会\">  \n" +
                "                        信工学院举行国家级课题申报经验交流会\n" +
                "            </a>\n" +
                "        \n" +
                "        </span>\n" +
                "        </td>  \n" +
                "        \n" +
                "        <td width=1% nowrap valign=\"middle\" align=right  class=\"timestyle55496\" >2017/10/15&nbsp;</td>     \n" +
                "        \n" +
                "      </tr>\n";
//        String regex="(<tr height=\"30\")([\\s\\S]*?)(</tr>)";
//        Pattern p=Pattern.compile(regex);
//        Matcher m=p.matcher(s);
//        while(m.find()){
//            System.out.print(m.group());
//            System.out.println("************");
//        }
//        specialSplit(s);

    }

    private static void specialSplit(String s) {
        String regex = "(<tr height=\"30\")([\\s\\S]*?)(</tr>)";
        String regex1 = "(xx_nry.jsp)(.*?)(archive=0)";
        String regex2 = "(title=\")(.*?)(\">)";
        String regex3 = "(timestyle55496\" >)(.*?)(&nbsp;)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        while (m.find()) {
            String tmp = m.group();
//            System.out.print(m.group());
//            System.out.println("************");
            Pattern p1 = Pattern.compile(regex1);
            Matcher m1 = p1.matcher(tmp);
            if (m1.find()) {
                System.out.println("http://info.zufe.edu.cn/" + m1.group());
            }
            Pattern p2 = Pattern.compile(regex2);
            Matcher m2 = p2.matcher(tmp);
            if (m2.find()) {
                System.out.println(m2.group().substring(7, m2.group().lastIndexOf('"')));
            }
            Pattern p3 = Pattern.compile(regex3);
            Matcher m3 = p3.matcher(tmp);
            if (m3.find()) {
                System.out.println(m3.group().substring(17, 27));
            }
        }
    }

}
