package com.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pet {

    @Schema(example = "10", description = "")
    private Long id;

    @Schema(example = "doggie", description = "")
    @NotNull
    private String name;

    @Schema(description = "")
    private Category category;

    @Schema(description = "")
    @NotNull
    @XmlElementWrapper
    @XmlElement(name = "photoUrl")
    private List<String> photoUrls = new ArrayList<String>();

    @Schema(description = "")
    @XmlElementWrapper
    private List<Tagg> tags;

    @XmlElementWrapper
    @XmlElement(name = "mapperCat")
    private String[] mapperCats;

    @Schema(enumAsRef = true, description = "pet status in the store")
    private StatusName status;

    @Data
    @Builder
    @XmlRootElement(name = "Taggg")
    public static class Tagg {

        @Schema(description = "")
        private Long id;

        @Schema(description = "")
        private String name;
    }

    @Data
    @Builder
    public static class Category {

        @Schema(example = "1", description = "")
        private Long id;

        @Schema(example = "Dogs", description = "")
        private String name;

        private StatusName status;
    }

    public enum StatusName {
        AVAILABLE("available"),

        PENDING("pending"),

        SOLD("sold");

        private String value;

        StatusName(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static StatusName fromValue(String text) {
            for (StatusName b : StatusName.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }
}
