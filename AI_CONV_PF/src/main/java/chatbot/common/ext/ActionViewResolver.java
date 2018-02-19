package chatbot.common.ext;

import java.util.Locale; 

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesView;

public class ActionViewResolver extends UrlBasedViewResolver implements Ordered {

	@Override
	protected View loadView(String viewName, Locale locale) throws Exception {
		AbstractUrlBasedView view = buildView(viewName);
		View viewObj = (View) getApplicationContext().getAutowireCapableBeanFactory().initializeBean(view, viewName);
		if (viewObj instanceof TilesView) {
			TilesView tv = (TilesView) viewObj;
			if (tv.getBeanName().indexOf(".action") != -1 || tv.getBeanName().indexOf(".empty") != -1 || tv.getBeanName().indexOf(".rs") != -1) {
				return null;
			}
		}
		return viewObj;
	}

}
