import {
    editUserData,
    deleteUser,
    requestSellerRole
} from "../actions/userActions.js";

import {
    setEventListener
} from "../utils/utils.js";

setEventListener("confirmEditUserDataButton", editUserData);
setEventListener("confirmDeletionOfUserButton", deleteUser)
setEventListener("requestSellerRoleButton", requestSellerRole)
