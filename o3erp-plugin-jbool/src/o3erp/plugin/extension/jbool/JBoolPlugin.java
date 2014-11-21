package o3erp.plugin.extension.jbool;

import java.util.Map;
import java.util.Set;

import o3erp.plugin.extension.IExpression;

import org.apache.commons.lang.StringUtils;

import com.bpodgursky.jbool_expressions.ExprUtil;
import com.bpodgursky.jbool_expressions.Expression;
import com.bpodgursky.jbool_expressions.parsers.ExprParser;
import com.bpodgursky.jbool_expressions.rules.RuleSet;

import ro.fortsoft.pf4j.Extension;
import ro.fortsoft.pf4j.Plugin;
import ro.fortsoft.pf4j.PluginWrapper;
import ro.fortsoft.pf4j.RuntimeMode;

public class JBoolPlugin extends Plugin {

	public JBoolPlugin(PluginWrapper wrapper) {
		super(wrapper);
	}

	@Override
    public void start() {
        System.out.println("JBoolPlugin.start()");
        // for testing the development mode
        if (RuntimeMode.DEVELOPMENT.equals(wrapper.getRuntimeMode())) {
            System.out.println(StringUtils.upperCase("JBoolPlugin"));
        }
    }

    @Override
    public void stop() {
        System.out.println("JBoolPlugin.stop()");
    }

    @Extension
    public static class JBoolExpression implements IExpression {

		@Override
		public Set<String> getVariables(String sExpr) {
			Expression<String> expr = ExprParser.parse(sExpr);
			return ExprUtil.getVariables(expr);
		}

		@Override
		public String evalExpr(String sExpr, Map<String, Boolean> variables) {
			Expression<String> expr = ExprParser.parse(sExpr);
			return RuleSet.assign(expr, variables).toString();
		}

    }

}
