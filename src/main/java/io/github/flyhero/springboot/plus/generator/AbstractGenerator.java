package io.github.flyhero.springboot.plus.generator;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import io.github.flyhero.springboot.plus.config.PlusConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/4/1 21:44
 */
@Slf4j
public abstract class AbstractGenerator implements InitializingBean {

    private static Configuration cfg;

    static {
        try {
            cfg = new Configuration(Configuration.VERSION_2_3_31);
            File file = new File(System.getProperty("user.dir") + "/src/main/resources/templates");
            cfg.setDirectoryForTemplateLoading(file);
            cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 模板方法模式
     *
     * @param plusConfig
     */
    public void process(PlusConfig plusConfig) {
        if (!isCreate(plusConfig)) {
            return;
        }
        if (isCustomProcess()) {
            Object o = customProcessFun(plusConfig);
            log.info("执行了自定义处理流程：{}", o);
            return;
        }
        String fileName = getFullFilePath(plusConfig);
        Object dataModel = getDataModel(plusConfig);
        String template = getTemplate();
        try {
            createFile(new File(fileName), template, dataModel);
        } catch (IOException | TemplateException e) {
            log.error("生成文件失败：{}", fileName, e);
        }
        log.info("生成文件：{}", fileName);
    }

    /**
     * 钩子方法，是否需要创建
     *
     * @param plusConfig 配置
     * @return 是否需要创建
     */
    public boolean isCreate(PlusConfig plusConfig) {
        return true;
    }

    /**
     * @return 是否需要自定义处理流程
     */
    public boolean isCustomProcess() {
        return false;
    }

    /**
     * 不适合普通处理流程，可自定义处理流程
     *
     * @param plusConfig 配置
     */
    public Object customProcessFun(PlusConfig plusConfig) {
        return null;
    }

    public abstract String getFullFilePath(PlusConfig plusConfig);

    public abstract String getTemplate();

    public abstract Object getDataModel(PlusConfig plusConfig);


    /**
     * 实现 InitializingBean，初始化完成后将自己注册到集合中
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        GeneratorRegistrar.register(this);
    }


    /**
     * 创建文件
     *
     * @param file      要创建的文件
     * @param ftl       模板
     * @param dataModel 数据模型
     * @throws IOException       IO异常
     * @throws TemplateException 模板解析异常
     */
    protected void createFile(File file, String ftl, Object dataModel) throws IOException, TemplateException {
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
        try {
            cfg.getTemplate(ftl, StandardCharsets.UTF_8.name()).process(dataModel, outputStreamWriter);
        } finally {
            outputStreamWriter.flush();
            outputStreamWriter.close();
        }
    }


}
