#!/usr/bin/env node
import * as cdk from "aws-cdk-lib";
import { BlogFrontendStack } from "../lib/blog-frontend-stack";

const app = new cdk.App();
new BlogFrontendStack(app, "BlogFrontendStack", {});
