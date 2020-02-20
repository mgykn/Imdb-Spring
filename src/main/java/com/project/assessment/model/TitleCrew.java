package com.project.assessment.model;

import java.util.List;

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
@Document(collection = "imdb_title_crew")
public class TitleCrew {
		
	
	@Field("tconst")
	@JsonProperty("tconst")
	@Indexed(unique = true)
	private String tconst;

	@Field("directors")
	@JsonProperty("directors")
	private List<String> directors;
	
	@Field("writers")
	@JsonProperty("writers")
	private List<String> writers;	
	

}
