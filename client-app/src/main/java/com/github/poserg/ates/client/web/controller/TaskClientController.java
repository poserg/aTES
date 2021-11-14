package com.github.poserg.ates.client.web.controller;

import com.github.poserg.ates.client.web.model.TaskModel;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
@Slf4j
public class TaskClientController {

    @Value("${task-tracker-service.api.url}")
    private String serviceApiUrl;

    @Autowired
    private WebClient webClient;

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        List<TaskModel> tasks = this.webClient.get()
                .uri(serviceApiUrl)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<TaskModel>>() {
                })
                .block();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute TaskModel task, BindingResult errors, Model model) {
        log.debug("Start save: {}", task);
        model.addAttribute("task", task);
        return "task";
    }
}
