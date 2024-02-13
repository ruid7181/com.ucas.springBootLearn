package com.ucas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/py", method = {RequestMethod.GET, RequestMethod.POST})
public class EditorController {

    public List<String> execPy(String pythonFilePath) {
        List<String> res = new ArrayList<>();
        try {
            String pythonInterpreterPath = "C:\\MySoftwares\\Anaconda\\envs\\gis\\python.exe";
//            String pythonFilePath = "C:\\Users\\empir\\Desktop\\demo.py";
            Process proc;
            proc = Runtime.getRuntime().exec(pythonInterpreterPath + " " + pythonFilePath);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
                    res.add(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
            res.add("python执行失败.");
            return res;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping("/editor")
    public ModelAndView toEditor(){

        System.out.println("Going to code editor.");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("CodeEditor");
        return modelAndView;
    }

    @RequestMapping("/result")
    public ModelAndView pyScriptHandler(@RequestParam("script") String script){

        String saveFilePath = "C:\\MyProjects\\java\\springBootLearn\\src\\main\\users\\";
        String dateNow = (new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")).format(new Date());

        java.io.FileWriter fw;
        try {
            fw = new java.io.FileWriter(saveFilePath + dateNow +".py");
            fw.write(script);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("pyError");
            modelAndView.addObject(e.toString());
            return modelAndView;
        }

        List<String> execRes = execPy(saveFilePath + dateNow +".py");


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pyResult");
        modelAndView.addObject("execRes", execRes);
        System.out.println(execRes);
        return modelAndView;
    }
}