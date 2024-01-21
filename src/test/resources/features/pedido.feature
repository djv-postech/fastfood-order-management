# language: pt

  Funcionalidade: Pedido

    Cenario: Cadastrar novo pedido
      Quando cadastrar um novo pedido
      Entao o pedido é criado e enviado com sucesso
      E deve ser apresentado

    Cenario: Cadastrar novo pedido com dados do cliente inválidos
     Quando receber um pedido com dados de cliente inválidos
     Entao uma mensagem de erro deve ser apresentada
#
