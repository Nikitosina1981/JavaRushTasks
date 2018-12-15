package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class HtmlView implements View
{
    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replace(".",
            "/") + "/vacancies.html";
    private Controller controller;

    @Override
    public void update(List<Vacancy> vacancies)
    {
        System.out.println(filePath);
        updateFile(getUpdatedFileContent(vacancies));
    }

    @Override
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod()
    {
        controller.onCitySelect("Moscow");
    }

    private String getUpdatedFileContent(List<Vacancy> list)
    {
        Document doc = null;
        try
        {
            doc = getDocument();
            Element element = doc.getElementsByClass("template").first();
            Element clonedElement = element.clone();
            clonedElement.removeClass("template");
            clonedElement.removeAttr("style");
            doc.select("tr[class=vacancy]").remove().not("tr[class=vacancy template]");
            for (Vacancy v: list)
            {
                Element newClone = clonedElement.clone();
                newClone.getElementsByClass("city").first().text(v.getCity());
                newClone.getElementsByClass("companyName").first().text(v.getCompanyName());
                newClone.getElementsByClass("salary").first().text(v.getSalary());
                Element link =newClone.getElementsByTag("a").first();
                link.text(v.getTitle());
                link.attr("href", v.getUrl());
                element.before(newClone.outerHtml());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return "Some exception occurred";
        }
        return doc.html();
    }

    private void updateFile(String s)
    {
        try (FileWriter fileWriter = new FileWriter(filePath))
        {
            fileWriter.write(s);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException
    {
        Document doc = Jsoup.parse(Paths.get(filePath).toFile(), "UTF-8");
        return doc;


    }
}
