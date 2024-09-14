import SwiftUI
import shared

struct AboutListView: View {
	private struct RowItem: Hashable {
	let title: String
	let subTitle: String
	}
}

private let items: [RowItem] = {
    let platform = Plaform()
    platform.logSystemInfo()

    var result: [RowItem] = [
        .init(
            title: "Operation System",
            subTitle: "\(plaform.osName) \(platform.osVersion)"
        ),
        .init(
            title: "Device",
            subTitle: plaform.deviceModel
        ),
        .init(
             title: "Density",
             subTitle: "@\(plaform.deviceModel)x"
        )
    ]
    return result
}()

var body: some View {
    List {
        ForEach(
            items, id \.self { item in
                VStack(aligment: .leading) {
                    Text(item.title)
                        .font(.footnote)
                        .foregroundStyle(.secondary)
                    Text(item.subTitle)
                        .font(.body)
                        .foregraundStyle(.primary)
                }
                .padding(.vertical, 4)
            }
        )
    }
}
