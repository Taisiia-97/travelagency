package com.taisiia.travelagency.domain.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.taisiia.travelagency.validator.DiscountStatusValid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@DiscountStatusValid
public class DiscountForm {
    private Long id;
    @NotBlank
    @Length(min = 3, max = 255)
    @Pattern(regexp = "[A-Z]+[a-z]*")
    private String name;
    private String code;
    @NotNull
    @Positive
    private Double percent;
    @NotBlank
    private String  status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss[.SSS][.SS][.S]", shape = JsonFormat.Shape.STRING)
    private LocalDateTime endDate;
}
