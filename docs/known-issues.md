# Known Issues
Because of our close partnership with workload teams who surface API functionality through Graph, it is not always appropriate or feasible to introduce fixes to missing or broken functionality.

## No Annotation Support
[OData](http://docs.oasis-open.org/odata/odata/v4.0/errata03/os/complete/part3-csdl/odata-v4.0-errata03-os-part3-csdl-complete.html#_Toc453752630) allows workloads to specify additional information about their API surface including both functional and non-functional stipulations. We currently do not support these annotations in the [Generator](https://github.com/microsoftgraph/MSGraph-SDK-Code-Generator), due to hierarchical inheritance issues regarding said annotations. Therefore, there may be methods that do not produce valid queries to Graph. Please refer to the [Graph Docs](https://developer.microsoft.com/en-us/graph/docs/concepts/overview) as the source of truth in these discrepancies.

## PATCH of OneNote Pages
OneNote currently defines the ability to PATCH a page's content (```PATCH https://graph.microsoft.com/v1.0/me/onenote/pages/id/content```), which is currently not supported by the Android SDK. If you are looking to PATCH JSON to a page, you can use the ```getOnenotePatchContent``` method to submit JSON data. If you are looking to PATCH multipart data, you cannot use the request builders given. Refer to the [documentation on making custom queries](https://github.com/microsoftgraph/msgraph-sdk-android/blob/master/docs/custom-queries.md) using the SDK.
