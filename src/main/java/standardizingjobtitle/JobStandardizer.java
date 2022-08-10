package standardizingjobtitle;

import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.Objects;


/**
 * A class whose standardize method operates on a given job title
 *
 * @author  Josh Waterson
 */
public class JobStandardizer {
    
    /**
     * Array of standardized job titles
     */
    private static final String[] STANDARDIZED_JTS = new String[]{
                    "Architect",
                    "Software engineer",
                    "Quantity surveyor",
                    "Accountant"};

    /**
     * Object that calculates Levenshtein distance between two Strings
     * to determine similarity.
     */
    private final LevenshteinDistance ld;

    public JobStandardizer() {
        ld = new LevenshteinDistance();
    }

    /**
     * Compares passed String with standardized job titles
     * and outputs the standardized job title that most closely
     * matches the passed input using Apache StringUtils'
     * Levenshtein Distance algorithm implementation.
     * 
     * @param jt    inputted job title
     * @return      the standardized job title that best matches 
     *              the 'ideal' standardized job titles
     */
    public String standardize(String jt) {
        if (Objects.isNull(jt) || jt.length() == 0) {
            throw new IllegalArgumentException();
        }

        String bestFit = null;
        int lowest = Integer.MAX_VALUE;
        int temp;
        jt = jt.toLowerCase();

        for (String s : STANDARDIZED_JTS) {
            temp = Math.min(ld.apply(jt, s.toLowerCase()), lowest);
            if (temp < lowest) {
                lowest = temp;
                bestFit = s;
            }
        }

        return bestFit;
    }

    public static void main(String[] args) {
        String jt = "Java engineer";
        JobStandardizer s = new JobStandardizer();
        String standardizedTitle = s.standardize(jt);
        System.out.println(standardizedTitle);

        jt = "C# engineer";
        standardizedTitle = s.standardize(jt);
        System.out.println(standardizedTitle);

        jt = "Chief Accountant";
        standardizedTitle = s.standardize(jt);
        System.out.println(standardizedTitle);
    }

}
