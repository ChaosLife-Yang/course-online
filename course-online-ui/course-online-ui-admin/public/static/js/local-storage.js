const LOCAL_KEY_REMEMBER_USER = "LOCAL_KEY_REMEMBER_USER"; // 记住我
const ACCESS_TOKEN = "accessToken"; // 令牌
const REFRESH_TOKEN = "refreshToken"; // 刷新令牌
const USER_INFO = "userInfo"; // 登录信息
const REFRESH_INFO = "refresh"; // 刷新令牌信息

const LocalStorage = {
    get: function (key) {
        let v = localStorage.getItem(key);
        if (v && typeof(v) !== "undefined" && v !== "undefined") {
            return JSON.parse(v);
        }
    },
    set: function (key, data) {
        localStorage.setItem(key, JSON.stringify(data));
    },
    remove: function (key) {
        localStorage.removeItem(key);
    },
    clearAll: function () {
        localStorage.clear();
    }
};
