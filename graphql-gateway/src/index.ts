import { ApolloServer } from '@apollo/server';
import { startStandaloneServer } from '@apollo/server/standalone'
import { ApolloGateway, IntrospectAndCompose } from '@apollo/gateway';

// Read superschem from file
// const __filename = fileURLToPath(import.meta.url);
// const __dirname = dirname(__filename);
// const dirPath = path.join(__dirname, '/schema.graphql');
// const supergraphSdl = readFileSync(dirPath, { encoding: 'utf-8' }).toString();

// Initialize an ApolloGateway instance and pass it
// the supergraph schema as a string
const gateway = new ApolloGateway({
    supergraphSdl: new IntrospectAndCompose({
        subgraphs: [
            { name: 'persoon', url: 'http://localhost:8080/graphql' },
            { name: 'autorisatie', url: 'http://localhost:8081/graphql'},
            { name: 'organisatie', url: 'http://localhost:8082/graphql'}
            // ...additional subgraphs...
        ],
        pollIntervalInMs: 10000
    }),
});

// Pass the ApolloGateway to the ApolloServer constructor
const server = new ApolloServer({
    gateway,
});

async function startApolloServer() {
    const { url } = await startStandaloneServer(server, { listen: { port: 4000 } });
    console.log(`🚀 Server listening at: ${url}`);
}

startApolloServer();
