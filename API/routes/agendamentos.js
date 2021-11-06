const express = require('express')
const router = express.Router()

router.get('/', (req,res,next) => {
    res.status(200).send({
        mensagem:"tudo ok"
    })
})

router.post('/', (req,res,next) => {
    const agendamento = {
        
    }
})
module.exports =  router    