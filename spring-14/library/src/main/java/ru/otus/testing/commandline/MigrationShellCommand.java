package ru.otus.testing.commandline;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDateTime;

@ShellComponent
public class MigrationShellCommand {
    private final Job transferJob;

    private final JobLauncher launcher;


    public MigrationShellCommand(Job transferJob, JobLauncher launcher) {
        this.transferJob = transferJob;
        this.launcher = launcher;
    }

    @ShellMethod(value = "Transfer data From h2 to mongo", key = {"-t", "-transfer"})
    public String transferData() {
        try {
            launcher.run(transferJob, new JobParametersBuilder()
                    .addLocalDateTime("time", LocalDateTime.now())
                    .toJobParameters());

            return "Transfer success";
        } catch (JobExecutionAlreadyRunningException | JobRestartException
                 | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            throw new IllegalStateException("Transfer fault: ", e.getCause());
        }

    }
}
