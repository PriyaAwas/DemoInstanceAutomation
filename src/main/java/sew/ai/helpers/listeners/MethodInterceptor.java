package sew.ai.helpers.listeners;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import sew.ai.helpers.props.Constants;
import sew.ai.utils.ExcelUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MethodInterceptor implements IMethodInterceptor {

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        List<Map<String, String>> list = ExcelUtils.getTestDetails(Constants.RUN_MANAGER_SHEETNAME);
        List<IMethodInstance> result = new ArrayList<>();
        for (int i = 0; i < methods.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (methods.get(i).getMethod().getMethodName().equalsIgnoreCase(list.get(j).get("TestName")) &&
                        list.get(j).get("Execute").equalsIgnoreCase("Yes")) {
                    methods.get(i).getMethod().setDescription((list.get(j).get("TestDescription")));
                    // methods.get(i).getMethod().setInvocationCount(Integer.parseInt(list.get(j).get("Count")));
                    methods.get(i).getMethod().setPriority(Integer.parseInt(list.get(j).get("Priority")));
                    result.add(methods.get(i));
                }
            }
        }
        return result;
    }
}
