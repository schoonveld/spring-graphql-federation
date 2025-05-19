import * as esbuild from 'esbuild'
import graphqlLoaderPlugin from "@luckycatfactory/esbuild-graphql-loader";

await esbuild.build({
    entryPoints: ['./src/index.ts'],
    bundle: true,
    format: "cjs",
    platform: 'node',
    target: 'node22',
    outfile: 'dist/index.cjs',
    plugins: [graphqlLoaderPlugin.default()],
});
