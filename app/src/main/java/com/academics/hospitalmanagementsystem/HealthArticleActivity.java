package com.academics.hospitalmanagementsystem;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HealthArticleActivity extends AppCompatActivity {
    TextView articleContent;
    TextView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article);

        articleContent = findViewById(R.id.articleContent);
        btnBack = findViewById(R.id.backOption);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticleActivity.this, HomeActivity.class));
            }
        });
        fetchHealthArticle();
    }

    private void fetchHealthArticle(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gnews.io/api/v4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsAPIInterface newsAPI = retrofit.create(NewsAPIInterface.class);

        Call<NewsResponse> call = newsAPI.getHealthArticles("health", "en", "pk","3786a9259afdcfc321b073c252df7a75");

        call.enqueue(new Callback<NewsResponse>(){
                @Override
                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response){
                    if (response.isSuccessful() && response.body() != null) {
                        List<Article> articles = response.body().getArticles();
                        if (articles != null && !articles.isEmpty()) {
                            int index = Calendar.getInstance().get(Calendar.DAY_OF_YEAR) % articles.size();
                            Article today = articles.get(index);
                            articleContent.setText(today.getTitle() + "\n\n" + today.getDescription());
                        } else {
                            articleContent.setText("No articles available");
                        }
                    } else {
                        articleContent.setText("Error fetching articles");
                    }
                }
            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t){
                articleContent.setText("Failed to fetch articles: " + t.getMessage());
            }
        });
    }
}