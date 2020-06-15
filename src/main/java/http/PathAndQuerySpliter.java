package http;

import com.github.jknack.handlebars.internal.lang3.StringUtils;
import http.Const.HttpConst;

public class PathAndQuerySpliter {

    public static PathAndQueryString splitPath(String allRequestPath) {
        String[] values = allRequestPath.split(HttpConst.PATH_QUERY_SEPARATOR);

        if (values.length < 2) {
            // queryString이 없는 path 일때
            return new PathAndQueryString(values[0], new QueryString(StringUtils.EMPTY));
        }
        return new PathAndQueryString(values[0], new QueryString(values[1]));
    }
}