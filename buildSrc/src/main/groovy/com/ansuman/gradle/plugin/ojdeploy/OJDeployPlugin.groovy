package com.ansuman.gradle.plugin.ojdeploy


import org.gradle.api.Plugin
import org.gradle.api.Project

public class OJDeployPlugin implements Plugin<Project>{
    @Override
    public void apply(Project project) {
        project.tasks.create("ojdeploy",OJDeploy.class,{

            it.setMessage("Hello")
            it.setRecipient("World")
        })

    }

}
