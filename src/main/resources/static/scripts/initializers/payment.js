import {
    uploadMoney,
    uploadMoneyWithAmount,
    withdrawMoneyWithAmount
} from "../actions/paymentActions.js";

import {
    setEventListener
} from "../utils/utils.js";

setEventListener("uploadWalletButton", uploadMoney);
setEventListener("uploadWalletWithAmountButton", uploadMoneyWithAmount)
setEventListener("withdrawWalletButton", withdrawMoneyWithAmount)