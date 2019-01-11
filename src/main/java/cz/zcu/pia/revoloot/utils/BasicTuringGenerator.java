package cz.zcu.pia.revoloot.utils;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BasicTuringGenerator implements ITuringGenerator {

    private Map<String, TuringQuestion> questionMap;

    private Random rnd;

    BasicTuringGenerator() {
        questionMap = new HashMap<>();
        rnd = new Random();
    }

    /**
     * Metoda ověří odpověď a odstraní položenou otázku.
     * Odpověď nejprve projde preprocessingem.
     *
     * @param answer hodnota odpovědi
     * @param uuid   jednoznačné označneí otázky
     * @return true, pokud je odpověď správná
     */
    @Override
    public boolean validateAnswer(String answer, String uuid) {
        if (answer == null || uuid == null) {
            return false;
        }
        TuringQuestion question = questionMap.getOrDefault(uuid, null);
        if (question == null) {
            return false;
        }
        questionMap.remove(uuid);
        answer = preprocessAnwer(answer);
        return question.getAnswer().equals(answer);
    }

    /***
     * Metoda vytvoří otázku a vrátí její UUID
     * @return uuid vytvořené otázky
     */
    @Override
    public String generateQuestion() {
        TuringQuestion question = generateCalendarQuestion();
        String uuid = question.getUuid();
        questionMap.put(uuid, question);
        return uuid;
    }

    /**
     * Metoda vrátí textovou reprezentaci dle vloženého UUID
     *
     * @return Otázka k zobrazení
     */
    @Override
    public String getQuestionRepresentation(String uuid) {
        TuringQuestion q = questionMap.getOrDefault(uuid, null);
        if (q == null) {
            return null;
        }
        return q.getQuestion();
    }

    //==============================================================================================================
    //==============================================================================================================
    //==============================================================================================================
    //==============================================================================================================

    /**
     * Metoda připraví odpověď k porovnání
     * sjednotí velikost písmen
     * ořeže vstup
     *
     * @param answer odpověď pro předzpracování
     * @return předzpracovná odpověď
     */
    private String preprocessAnwer(String answer) {
        return answer.toLowerCase().trim();
    }

    /**
     * Metoda náhodně připraví jednu z otázek
     * Kolikátý je...
     *
     * @return připravená otázka
     */
    private TuringQuestion generateCalendarQuestion() {
        UUID uuid = UUID.randomUUID();
        while (questionMap.containsKey(uuid.toString())) {
            uuid = UUID.randomUUID();
        }

        String question;
        String answer;
        int index = rnd.nextInt(5);
        switch (index) {
            case 0:
                question = "Jaká je teď hodina (24H)?";
                answer = Integer.toString(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
                break;
            case 1:
                question = "Kolikátý je měsíc?";
                answer = Integer.toString(Calendar.getInstance().get(Calendar.MONTH)+1);
                break;
            case 2:
                question = "Jaký je rok?";
                answer = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
                break;
            case 3:
                question = "Kolikátý je den v měsíci?";
                answer = Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                break;
            default:
                question = "Kolik má člověk prstů na dvou rukách?";
                answer = "10";
        }
        return new TuringQuestion(uuid.toString(), question, answer);
    }

}

/**
 * Přepravka na objekt turingovské otázky
 */
class TuringQuestion {
    private String uuid;
    private String question;
    private String answer;

    TuringQuestion(String uuid, String question, String answer) {
        this.uuid = uuid;
        this.question = question;
        this.answer = answer;
    }

    String getUuid() {
        return uuid;
    }

    String getQuestion() {
        return question;
    }

    String getAnswer() {
        return answer;
    }

    @Override
    public String toString(){
        return question + " Je "+ answer;
    }
}
