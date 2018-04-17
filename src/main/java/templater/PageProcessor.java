package templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class PageProcessor {
    private static PageProcessor instance;
    private final Configuration config;

    private PageProcessor() {
        this.config = new Configuration(Configuration.VERSION_2_3_28);

        try {
            config.setDirectoryForTemplateLoading(new File(
                    "D:\\Users\\vikul\\Documents\\Coding\\Projects\\Vermilion_webapp\\web\\templates"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        config.setDefaultEncoding("UTF-8");
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    public static PageProcessor getInstance() {
        if (instance == null) instance = new PageProcessor();
        return instance;
    }

    public String processPage(String filename, Map<String, Object> data) {
        Writer result = new StringWriter();
        try {
            Template template = config.getTemplate(filename);
            template.process(data, result);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
