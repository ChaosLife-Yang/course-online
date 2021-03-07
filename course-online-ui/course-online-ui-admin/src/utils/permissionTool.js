export function hasPermission(resource,authorities) {
    return authorities.indexOf(resource) !== -1;
}

export function hasMenuPermission(resource,authorities) {
    for (let i = 0; i < authorities.length; i++) {
        if (authorities[i].match(resource)) {
            return true;
        }
    }
    return false;
}