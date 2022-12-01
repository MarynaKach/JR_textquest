package textquest.servlets;

import textquest.service.MessageService;
import textquest.service.StoryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class AppServletTest {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    @Mock
    HttpSession session;
    ServletConfig servletConfig = mock(ServletConfig.class);
    @Mock
    ServletContext context;
    @Mock
    RequestDispatcher requestDispatcher;
    MessageService messageService = mock(MessageService.class);
    @Mock
    StoryBuilder storyBuilder;
    AppServlet appServlet;

    @BeforeEach
    public void setUp() throws ServletException {
        when(servletConfig.getServletContext())
                .thenReturn(context);
        when(request.getSession())
                .thenReturn(session);
        appServlet = new AppServlet();
        appServlet.init(servletConfig);
    }

    @Test
    public void doGet() throws ServletException, IOException {
        when(request.getParameter("messageId"))
                .thenReturn("1");
        when(messageService.getMessageById(1, storyBuilder))
                .thenReturn("message");
        when(messageService.getLink("yes", 1, storyBuilder))
                .thenReturn("linkYes");
        when(messageService.getLink("no", 1, storyBuilder))
                .thenReturn("linkNo");
        when(messageService.getIdOfPicture(1, storyBuilder))
                .thenReturn(1);
        when(context.getRequestDispatcher(eq("/WEB-INF/quest.jsp")))
                .thenReturn(requestDispatcher);
        appServlet.doGet(request, response);
        verify(requestDispatcher, times(1))
                .forward(request, response);
    }

    @Test
    public void doPost() throws IOException, ServletException {
        when(request.getParameter("name"))
                .thenReturn("testName");
        when(request.getSession()).thenReturn(session);
        when(messageService.getMessageById(1, storyBuilder))
                .thenReturn("message");
        when(messageService.getLink("yes", 1, storyBuilder))
                .thenReturn("linkYes");
        when(messageService.getLink("no", 1, storyBuilder))
                .thenReturn("linkNo");
        when(messageService.getIdOfPicture(1, storyBuilder))
                .thenReturn(1);
        when(context.getRequestDispatcher(eq("/WEB-INF/quest.jsp")))
                .thenReturn(requestDispatcher);
        appServlet.doPost(request, response);
        verify(requestDispatcher, times(1))
                .forward(request, response);
    }
}
