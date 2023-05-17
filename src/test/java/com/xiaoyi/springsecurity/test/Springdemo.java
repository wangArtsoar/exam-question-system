package com.xiaoyi.springsecurity.test;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王艺翔
 * @description springdemo
 * @date 2023/5/17 14:37
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
public class Springdemo {
	public static void main(String[] args) {
		//该集合用来存储书本信息
		List<String> bookList = new ArrayList<>();
		/**********  Begin  **********/
		SAXReader reader = new SAXReader();
		//解析文档这里需要抛出DocumentException异常。
		try {
			//1.获取Document对象
			Document document = reader.read(new File("D:\\A_IDEworkspace\\springboot\\spring-security\\src\\test\\java\\com\\xiaoyi\\springsecurity\\test\\books.xml"));
			//2.获取根节点
			Element rootElm = document.getRootElement();
			//3.获取根节点下所有子节点
			List<Element> elms = rootElm.elements("book");
			//4.遍历子节点并将文档中信息添加到集合
			elms.forEach(element -> {
				String text = element.getText();
				String author = element.attribute("author").getText();
				System.out.println(text + "----" + author);
			});
			//5.遍历输出文档信息
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		/**********   End   **********/
	}
}
