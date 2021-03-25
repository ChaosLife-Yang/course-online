export function parseInfo(jwt) {
    let strings = jwt.split(".");
    //解析，需要吧‘_’,'-'进行转换否则会无法解析
    return JSON.parse(decodeURIComponent(escape(window.atob(strings[1].replace(/-/g, "+").replace(/_/g, "/")))));
}