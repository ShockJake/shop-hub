import {
    uploadMoney
} from "../actions/paymentActions.js";

import {
    setEventListener
} from "../utils/utils.js";

setEventListener("uploadWalletButton", uploadMoney);