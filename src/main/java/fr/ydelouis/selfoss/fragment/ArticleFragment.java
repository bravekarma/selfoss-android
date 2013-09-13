package fr.ydelouis.selfoss.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.text.format.DateUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import fr.ydelouis.selfoss.R;
import fr.ydelouis.selfoss.entity.Article;

@EFragment(R.layout.fragment_article)
@OptionsMenu(R.menu.fragment_article)
public class ArticleFragment extends Fragment {

	@FragmentArg protected Article article;

	@ViewById protected WebView webView;
	@ViewById protected TextView title;
	@ViewById protected TextView dateTime;

	@AfterViews
	protected void iniViews() {
		webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		setArticle(article);
	}

	public void setArticle(Article article) {
		this.article = article;
		if (article != null) {
			title.setText(article.getTitle());
			dateTime.setText(DateUtils.getRelativeTimeSpanString(getActivity(), article.getDateTime()));
			webView.loadData(article.getContent(), "text/html", "utf-8");
		}
	}

	@OptionsItem(R.id.browser)
	protected void openInBrowser() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(article.getLink()));
		startActivity(intent);
	}
}