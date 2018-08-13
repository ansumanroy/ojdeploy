package com.ansuman.gradle.plugin.ojdeploy

import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.incremental.IncrementalTaskInputs
import org.gradle.jvm.tasks.Jar
import org.apache.tools.ant.taskdefs.condition.Os

public class OJDeploy extends Jar {
    private String message;
    private String recipient;
    private String workspace;
    private String currentWorkingDirectory;
    private String outputfile;
    private String profile;

    String getOraclehome() {
        return oraclehome
    }

    void setOraclehome(String oraclehome) {
        this.oraclehome = oraclehome
    }
    private String oraclehome;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    private String projectName;

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public String getCurrentWorkingDirectory() {
        return currentWorkingDirectory;
    }

    public void setCurrentWorkingDirectory(String currentWorkingDirectory) {
        this.currentWorkingDirectory = currentWorkingDirectory;
    }

    public String getOutputfile() {
        return outputfile;
    }

    public void setOutputfile(String outputfile) {
        this.outputfile = outputfile;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }


    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }



    @TaskAction
    void executeOJDeploy(IncrementalTaskInputs inputs) {
        println inputs.incremental ? 'CHANGED inputs considered out of date'
                : 'ALL inputs considered out of date'

        println 'Executing OJDeploy'
        def oracle_home=oraclehome
        def ojdeployExecutable=oracle_home+"/jdev/bin/ojdeploy "


        if(Os.isFamily(Os.FAMILY_WINDOWS)){
            ojdeployExecutable=oracle_home+"/jdev/bin/ojdeploy.exe "
        }

        def ojdeployproc=ojdeployExecutable+" -workspace $workspace -project $projectName -profile $profile -outputfile $outputfile"
        println ojdeployproc

        def process=ojdeployproc.execute()
        process.waitFor()
        process.in
        println process.err.text

    }
}
