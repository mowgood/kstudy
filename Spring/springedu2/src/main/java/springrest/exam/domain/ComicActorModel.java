package springrest.exam.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@AllArgsConstructor
public class ComicActorModel extends RepresentationModel<ComicActorModel> {
    private String name;
}
