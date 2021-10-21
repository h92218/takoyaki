package takoyaki.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONArray;

@Getter
@Setter
public class DataDto {
    int idx;
    String phone;
    String menu;
    String flavor;
    String completion;
    String registerDate;
    String completeDate;
    int waitingCount;
    int remainTime;
}
