package shubing.test2;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义爬虫类需要继承WebCrawler类，决定哪些url可以被爬以及处理爬取的页面信息
 */
public class MyCrawler extends WebCrawler {

    /**
     * 这个方法主要是决定哪些url我们需要抓取，返回true表示是我们需要的，返回false表示不是我们需要的Url
     * 第一个参数referringPage封装了当前爬取的页面信息
     * 第二个参数url封装了当前爬取的页面url信息
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
//        System.out.println("********* " + url.getURL());
        return url.getURL().contains("page=");
        //         String href = url.getURL().toLowerCase();  // 得到小写的url
//         return FILTERS.matcher(href).matches()   // 正则匹配，过滤掉我们不需要的后缀文件
//                && href.startsWith("https://jiashubing.cn");  // url必须是http://www.java1234.com/开头，规定站点
//                && href.startsWith("http://info.zufe.edu.cn/xx_nry.jsp");  // url必须是http://www.java1234.com/开头，规定站点
    }

    /**
     * 当我们爬到我们需要的页面，这个方法会被调用，我们可以尽情的处理这个页面
     * page参数封装了所有页面信息
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();  // 获取url
        System.out.println("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData) {  // 判断是否是html数据
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData(); // 强制类型转换，获取html数据对象
//             String text = htmlParseData.getText();  // 获取页面纯文本（无html标签）
            String html = htmlParseData.getHtml();  // 获取页面Html
//             Set<WebURL> links = htmlParseData.getOutgoingUrls();  // 获取页面输出链接

//             System.out.println("纯文本长度: " + text.length());
//             System.out.println("html长度: " + html.length());
//             System.out.println("输出链接个数: " + links.size());

//            System.out.println("html: " + html);
            specialSplit(html);


        }
    }

    private void specialSplit(String s) {
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
            if(m1.find()){
                String str = m1.group();
                int t1 = str.indexOf('\"');
                int t2 = str.indexOf('\"',t1+1);
                int t3 = str.indexOf('\"',t2+1);
                int t4 = str.indexOf('\"',t3+1);
                int t5 = str.indexOf('\"',t4+1);
                System.out.println(str.substring(t2+1, t3));
                System.out.println(str.substring(t4+1, t5));
            }
            Pattern p2 = Pattern.compile(regex2);
            Matcher m2 = p2.matcher(tmp);
            if(m2.find()){
                String str = m2.group();
                System.out.println(str.substring(str.indexOf("</span>")+7,str.lastIndexOf('<')));
            }
            Pattern p3 = Pattern.compile(regex3);
            Matcher m3 = p3.matcher(tmp);
            if(m3.find()){
                String str = m3.group();
                System.out.println(str.substring(str.indexOf("</span>")+7,str.lastIndexOf('<')));
            }
            Pattern p4 = Pattern.compile(regex4);
            Matcher m4 = p4.matcher(tmp);
            if(m4.find()){
                String str = m4.group();
                int t1 = str.indexOf("href") + 6;
                int t2 = str.indexOf('\"', t1 + 10);
                System.out.println("http://www.huodongxing.com" + str.substring(t1, t2));
            }

            System.out.println("\n\n");
        }
    }
}