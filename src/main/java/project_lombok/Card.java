package project_lombok;

import lombok.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode(of = "pan")
@AllArgsConstructor(staticName = "getInsatnce")
public class Card {
    @NonNull
    private String holderName;
    @NonNull
    private String pan;
    @NonNull
    private String expiry;

}
