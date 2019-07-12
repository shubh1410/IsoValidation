import java.util.Base64;

public class Orchestrator {

    public static void main(String[] args){
        XmlParser parser=new XmlParser();
        XmlParser.Record record=parser.Parser();
        Base64.Decoder decoder = Base64.getDecoder();
        String dStr = new String(decoder.decode(record.biometric_record));

        Iso_19794_6_Validator validator=new Iso_19794_6_Validator();

        if(validator.iris_validator(dStr))
            System.out.println("Valid");
        else
            System.out.println("Invalid");
    }
}
