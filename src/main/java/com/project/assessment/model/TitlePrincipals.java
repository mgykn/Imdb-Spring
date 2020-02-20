package com.project.assessment.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Document(collection = "imdb_title_principals")
public class TitlePrincipals {
		
 
	@Field("tconst")
	@JsonProperty("tconst")
	@Indexed
	private String tconst;

	@Field("ordering")
	@JsonProperty("ordering")
	private int ordering;

	@Field("nconst")
	@JsonProperty("nconst")
	private String nconst;
			
	@Field("category")
	@JsonProperty("category")
	private String category;
			
	@Field("job")
	@JsonProperty("job")
	private String job;
	
    @Field("characters")
    @JsonProperty("characters")
    private String characters ;

			
}
