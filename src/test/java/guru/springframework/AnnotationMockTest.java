package guru.springframework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Map;

import static org.mockito.MockitoAnnotations.initMocks;

public class AnnotationMockTest {

    @Mock
    Map<String, Object> mapMock;

    @BeforeEach
    void setup() {
        initMocks(this);
    }

    @Test
    void testMock() {
        mapMock.put("key", "value");
    }
}
