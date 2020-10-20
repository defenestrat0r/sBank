package com.imtrying.bank

class User {
    public var name = ""
    public var email = "johndoe@hotmail.com"
    public var bal = 0
    public var id = 0
    constructor(_name: String, _email: String, _bal: Int, _id: Int)
    {
        name = _name
        bal = _bal
        email = _email
        id = _id
    }

    constructor(_name: String, _email: String, _bal: Int)
    {
        name = _name
        email = _email
        bal = _bal
    }

    constructor()
    { }
}
