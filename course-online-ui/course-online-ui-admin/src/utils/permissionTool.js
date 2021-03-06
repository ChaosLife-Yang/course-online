const info = LocalStorage.get(USER_INFO) || {};
const authorities = info.authorities !== undefined ? info.authorities : [];

export function hasPermission(resource) {
    return authorities.indexOf(resource) !== -1;
}

export function hasMenuPermission(resource) {
    for (let i = 0; i < authorities.length; i++) {
        if (authorities[i].match(resource)) {
            return true;
        }
    }
    return false;
}