package test.servlets;

import main.com.javarush.textquest.enteties.Message;
import main.com.javarush.textquest.service.StoryBuilder;
import main.com.javarush.textquest.service.UserService;
import main.com.javarush.textquest.servlets.AppServlet;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AppServletTest {
        AppServlet appServlet = new AppServlet();

    @Test
    public void doGetSession() {
        HttpServletRequest requestMock = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse responseMock = Mockito.mock(HttpServletResponse.class);
        HttpSession httpSessionMock = Mockito.mock(HttpSession.class);
        //appServlet.doGet(requestMock, responseMock);
        verify(requestMock).getSession();
    }

    @Test
    public void doPost() throws IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpSession httpSession = Mockito.mock(HttpSession.class);
        UserService userServiceMock = Mockito.mock(UserService.class);
        appServlet.init();
        when(request.getSession()).thenReturn(httpSession);
        when(userServiceMock.isUserExist("username")).thenReturn(true);
        PrintWriter printWriterMock = Mockito.mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriterMock);
        //appServlet.doPost(request, response);
        verify(response).sendRedirect("quest");
    }

    @Test
    public void init() {
        StoryBuilder storyBuilder = new StoryBuilder();
        AppServlet appServlet = new AppServlet();
        when(new AppServlet()).then((Answer<?>) storyBuilder.buildStory());
        Set<Message> messages = new HashSet<>();
    }
}
