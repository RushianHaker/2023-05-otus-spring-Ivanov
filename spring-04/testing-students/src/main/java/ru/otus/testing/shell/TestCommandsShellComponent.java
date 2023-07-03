package ru.otus.testing.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.testing.model.User;
import ru.otus.testing.service.MessageSourceService;
import ru.otus.testing.service.TestService;
import ru.otus.testing.service.UserService;

@ShellComponent
public class TestCommandsShellComponent {
    private final TestService testService;

    private final UserService userService;

    private final MessageSourceService messageSourceService;

    private User user = null;

    public TestCommandsShellComponent(TestService testService, UserService userService, MessageSourceService messageSourceService) {
        this.testService = testService;
        this.userService = userService;
        this.messageSourceService = messageSourceService;
    }


    @ShellMethod(value = "login", key = {"l", "login"})
    private String login() {
        user = userService.handShakeWithUser();
        return messageSourceService.getMessage("shell_login", null);
    }

    @ShellMethod(value = "start-test", key = {"st", "start-test"})
    @ShellMethodAvailability(value = "checkLoginUser")
    public void startTesting() {
        testService.testing(user);
    }

    private Availability checkLoginUser() {
        return user == null ? Availability.unavailable(messageSourceService.getMessage("shell_failed_check_login_user", null)) :
                Availability.available();
    }
}
