package cz.zcu.pia.revoloot.utils;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class BasicTuringGeneratorTest {

    ITuringGenerator tg = new BasicTuringGenerator();

    String questionHours = "Jaká je teď hodina (24H)?";
    String questionMonth = "Kolikátý je měsíc?";
    String questionYear = "Jaký je rok?";
    String questionDay = "Kolikátý je den v měsíci?";
    String questionPerson = "Kolik má člověk prstů na dvou rukách?";

    String hours = "16";
    String month = "1";
    String year = "2019";
    String day = "10";
    String person = "10";

    @Test
    void validateAnswer() {
        String uuid = tg.generateQuestion();
        String question = tg.getQuestionRepresentation(uuid);

        if(question.equals(questionHours)){
            assertTrue(tg.validateAnswer(hours, uuid), "Chyba hodiny.");
        }

        if(question.equals(questionMonth)){
            assertTrue(tg.validateAnswer(month, uuid), "Chyba měsíc.");
        }

        if(question.equals(questionYear)){
            assertTrue(tg.validateAnswer(year, uuid), "Chyba rok.");
        }

        if(question.equals(questionDay)){
            assertTrue(tg.validateAnswer(day, uuid), "Chyba den.");
        }

        if(question.equals(questionPerson)){
            assertTrue(tg.validateAnswer(person, uuid), "Chyba osoba.");
        }
    }

    @Test
    void multipleValidate(){
        for (int i = 0; i < 1000; i++) {
            validateAnswer();
        }
    }

}