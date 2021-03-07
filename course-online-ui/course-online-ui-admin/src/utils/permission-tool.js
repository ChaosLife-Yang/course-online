function getAuthorities() {
    let info = LocalStorage.get(USER_INFO) || {};
    return info.authorities !== undefined ? info.authorities : [];
}

export function hasPermission(resource) {
    let authorities = getAuthorities();
    return authorities.indexOf(resource) !== -1;
}

export function hasMenuPermission(resource) {
    let authorities = getAuthorities();
    for (let i = 0; i < authorities.length; i++) {
        if (authorities[i].match(resource)) {
            return true;
        }
    }
    return false;
}