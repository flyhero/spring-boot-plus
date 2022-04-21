package io.github.flyhero.springboot.plus.controller;

import io.github.flyhero.springboot.plus.model.ProjectInfoReq;
import io.github.flyhero.springboot.plus.util.FileUtils;
import io.github.flyhero.springboot.plus.util.JsonUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/4/10 21:40
 */
@RestController
public class ProjectController {

    @GetMapping("/config")
    public Map getConfig() throws IOException {
        String data = FileUtils.readResourcesToString("/data/data.json");
        return JsonUtil.json2Object(data, Map.class);
    }

    @PostMapping("/generator")
    public void create(@RequestBody ProjectInfoReq projectInfoReq) {

    }
}
