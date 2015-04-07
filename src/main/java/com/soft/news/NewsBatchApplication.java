package com.soft.news;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.soft.news.domain.Article;
import com.soft.news.repository.ArticleRepository;

@SpringBootApplication
@EnableBatchProcessing
public class NewsBatchApplication {

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

	@Autowired
	private ArticleRepository articleRepository;

	@Bean
	protected Tasklet tasklet() {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext context) {
				Article article = new Article();
				article.setCateId(1);
				article.setContent("test321");
				article.setSubject("test123");
				article.setName("test231");
				article.setUrl("test312");

				article = articleRepository.save(article);
				System.out.println("test : " + article);

				return RepeatStatus.FINISHED;
			}
		};
	}

	@Bean
	public Job job() throws Exception {
		return this.jobs.get("job").incrementer(new RunIdIncrementer()).start(step1()).build();
	}

	@Bean
	protected Step step1() throws Exception {
		return this.steps.get("step1").tasklet(tasklet()).build();
	}

	public static void main(String[] args) throws Exception {
		// System.exit is common for Batch applications since the exit code can
		// be used to
		// drive a workflow
		System.exit(SpringApplication.exit(SpringApplication.run(NewsBatchApplication.class, args)));
	}
}
