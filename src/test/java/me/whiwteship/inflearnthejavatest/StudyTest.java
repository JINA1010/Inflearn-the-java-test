package me.whiwteship.inflearnthejavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName("스터디 만들기 \uD83D\uDE31")
    void create_new_study() {
       Study study = new Study(-10);

        assertNotNull(study);
        //assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 DRAFT 상태다.");
        //람다식을 사용하여 메세지 출력시 더 효율 비용이 좋다.
        assertEquals(StudyStatus.DRAFT, study.getStatus(),
                () -> "스터디를 처음 만들면" + StudyStatus.DRAFT + "상태다.");
        //assertTrue(1 < 2);
        assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다.");

    }

    @Test
    @DisplayName("한꺼번에 오류를 잡을 수 있다.")
    void create_new_study2() {
        Study study = new Study(-10);

        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(),
                        () -> "스터디를 처음 만들면" + StudyStatus.DRAFT + "상태다."),
                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다.")
        );
    }

    @Test
    @DisplayName("특정 throw를 던질 수 있다.")
    void create_new_study3() {
        //특정 오류를 발생시킬 수 있다.
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = exception.getMessage();
        assertEquals("limit은 0보다 커야 한다.", message);
    }

    @Test
    @DisplayName("TimeOut Test")
    void create_new_study4() {
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });

        /*
        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
        */

        /*
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });

        // TODO ThreadLocal
        */
    }

    @Test
    @DisplayName("조건에 따라 테스트")
    void create_new_study5() {
        String test_env = System.getenv("TEST_ENV");
        System.out.println(test_env);
        assumeTrue("LOCAL".equalsIgnoreCase(test_env));

        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            System.out.println("local");
            Study actual = new Study(100);
            assertThat(actual.getLimit()).isGreaterThan(0);
        });

        assumingThat("keesun".equalsIgnoreCase(test_env), () -> {
            System.out.println("keesun");
            Study actual = new Study(100);
            assertThat(actual.getLimit()).isGreaterThan(0);
        });

    }

    @Test
    @DisplayName("조건에 따라 테스트")
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void create_new_study6() {
        String test_env = System.getenv("TEST-ENV");
        System.out.println("local");
        Study actual = new Study(100);
        assertThat(actual.getLimit()).isGreaterThan(0);

    }

    @Test
    @DisplayName("조건에 따라 테스트")
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_11})
    void create_new_study7() {
        String test_env = System.getenv("TEST-ENV");
        System.out.println("local");
        Study actual = new Study(100);
        assertThat(actual.getLimit()).isGreaterThan(0);

    }

    @Test
    @DisplayName("조건에 따라 테스트")
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")
    void create_new_study8() {
        Study actual = new Study(100);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("스터디 만들기 😊😊😊😊")
    void create1_new_study_again() {
        System.out.println("create1");
    }


    @BeforeAll
    static void beforeAll(){
        System.out.println("Before all");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After each");
    }

}