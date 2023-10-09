# GoldenRaspberryAwards
API RESTful que fornece os vencedores da categoria Pior Filme do Golden Raspberry Awards.
# Requisitos
Para execução do projeto, é necessário instalação do JDK 11.
# Execução
Importar o projeto em alguma IDE como um projeto maven, nenhuma instalação externa é necessária.
Ao rodar a aplicação será feita automaticamente a leitura do arquivo movielist.csv que se encontra em src/main/resources/ e será populado os dados no banco de dados h2 que pode ser acessado através da url http://localhost:8080/api/h2.
Obs: Os testes integrados da aplicação necessitam serem executados separadamente após a inicialização da aplicação. 
# Endpoints disponíveis
Para ver a lista de chamadas REST disponíveis, seus parametros, códigos de resposta HTTP, e tipo de retorno, inicie a aplicação e acesse: http://localhost:8080/api/swagger-ui.html
