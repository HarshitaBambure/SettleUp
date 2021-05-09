package com.example.settleup.ui

class Debts {
    var CurrentUser: String = ""
    var ToWhome: String = ""
    var amount: Int = 0

    constructor(PaidBy: String, ToWhome: String, Amount: Int) {
        this.CurrentUser = PaidBy
        this.ToWhome = ToWhome
        this.amount = Amount
    }
}